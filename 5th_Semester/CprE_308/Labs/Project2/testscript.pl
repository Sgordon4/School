#!/usr/bin/perl
# CprE 308 Project 2 Test script
# Fall 2011
# 
# Download this script and make it executable (chmod +x scriptname)
# then run it using ./scriptname
#
# For testing your program, you can specify the number of threads,
# accounts, and the random seed used to generate them.
#
# For the final run to submit, use the default of 10 threads, 1000 accounts,
# and a seed of 0
use strict;
use warnings;
use integer;

if (@ARGV < 1) {
	print "Use: $0 <program> [<nthreads> [<naccounts> [<seed>]]]\n";
	exit 1;
}
my($prgm, $thread, $accts, $seed) = @ARGV;

$thread ||= 10;		# Default to 10 if no argument entered on command line
$accts ||= 1000;	# Default to 1000
$seed ||= 0;		# Default to 0

# If the test script is not working correctly it may be that your threads
# are taking longer to process the transactions than the test script expects.
# To fix this problem increase the testSleepTime variable value to sleep longer
# between transactions and balance checking. 
my $testSleepTime = 240 / $thread; # Sleep time in seconds


srand($seed + 5);		# Seed random num gen - Add 5... turns out seed of 0 not best test case

my $outfile = 'testout-'.$$;		# Name output file
my($c_in, $f_in, $c_out, $f_out);
pipe $f_in, $c_in;			# Open pipes
pipe $c_out, $f_out;
my $pid = fork;			# Fork

if ($pid == 0) {		# Child
	open STDIN, '<&', $f_in;
	open STDOUT, '>&', $f_out;
	close $c_in;
	close $c_out;
	close $f_in;
	close $f_out;
	exec $prgm, $thread, $accts, $outfile;	# Execute appserver in child
	print "Failed to execute $prgm\n";
	exit 1;
} else {			# Parent
	close $f_in;
	close $f_out;
}

# make the pipes autoflush
select $c_in; $| = 1;
select $c_out; $| = 1;
select STDOUT;

sub sr {
	my $line = shift;
	print "\e[33m$line\e[m\n";
	print $c_in "$line\n";
#	my $resp = <$c_out>;		# Prints ID response from appserver
#	chomp $resp;			# Causes program to block if appserver doesn't flush buffers
#	print "\e[32m$resp\e[m\n";
#	return $resp;
}

# Initialize all accounts with value of 10000000
my @acctBals;		# Keep track of balances in perl program
for my $acct (0..$accts-1) {
	$acctBals[$acct] = 1000000;	# Initialize accounts in perl
}

my $init_skip = 0;
for my $id (0..($accts/10 - 1)) {	# Initialize accounts in appserver
	my $line = 'TRANS';
	$line .= ' '.($id*10 + $_).' 1000000' for 1..10;
	sr $line;
	$init_skip++;
}

print "\e[35mWaiting for initial deposits to complete...\e[m\n";
my $sleepTime = ((($accts / 10) * 2)/$thread) + 5;	# Calculate sleep time
if ( $sleepTime < 15 ) { $sleepTime = 15; }
sleep $sleepTime;			# Sleep

for my $line (1..300) {		# Perform 300 Transactions
	my $line = "TRANS";
	my $bal = 0;
	my $len = int rand 6;		# Up to 6 accounts per transaction
	my %dups;			# Track duplicates
	my $ISF = int rand 100;		# 1 in 100 transactions will be invalid
	if( $ISF == 1) {$len = 6};	# If this trans invalid set length to 6
	my $iter = -1;
	my $set = 0;
	for (0..$len) {
		my $acct = 1 + int rand $accts;
		$iter++;
		next if $dups{$acct}++;
		my $amt = 0;
		if ( $ISF == 1 && $iter >= 3 && $set != 1) { # Transaction invalid - pick amount to force invalid
			$amt = -100 * $acctBals[$acct-1];
			$set = 1;
		}
		elsif( $ISF == 1) {	# Transaction is invalid - pick valid amounts
			$amt = $acctBals[$acct-1] - 2 * (int rand $acctBals[$acct-1]);
		}
		else{	# Transaction is valid
			$amt = $acctBals[$acct-1] - 2 * (int rand $acctBals[$acct-1]);
			$acctBals[$acct-1] += $amt;	# Track transactions in this program
		}
		$line .= " $acct $amt";
	}
	sr $line;
}

print "\e[35mWaiting for transactions to complete...\e[m\n";
$sleepTime = $testSleepTime;
if($sleepTime < 10) { $sleepTime = 10; }
sleep $sleepTime + 5;

for my $id (1..$accts) { # Submit checks for each account
	sr "CHECK $id";
}

print "\e[33mEND\e[m\n";
print $c_in "END\n";
print "\e[35mWaiting for program to exit...\e[m\n";
wait;

no integer;
print "\e[35mChecking output file: $outfile\e[m\n";
open F, $outfile;
my $trcount = 0;
my $trtime = 0;
my $balcount = 0;
my $baltime = 0;
my $total = 0;
while (<F>) {
	if ($init_skip-- > 0) {
		die "Bad (initial deposit) output line: $_" unless /^\d+ OK TIME/;
	} elsif (/ (OK|ISF \d+) TIME (\d+)(\.\d+) (\d+)(\.\d+)/) {
		$trtime += ($4 - $2) + ($5 - $3);
		$trcount++;
	} elsif (/^\d+ BAL (\d+) TIME (\d+)(\.\d+) (\d+)(\.\d+)/) {
		$total += $1;
		$baltime += ($4 - $2) + ($5 - $3);
		$balcount++;
	} else {
		die "Bad output line: $_";
	}
}
close F;

if ($balcount != $accts) {
	print "ERR: There were $accts accounts but only $balcount balance queries\n";
}
my $expectedSum = 0;
($expectedSum+=$_) for @acctBals;
if($total != $expectedSum) {
	print "ERR: Final balance of all $accts accounts is $total, expected $expectedSum\n";
}else{
	print "Final balance of all $accts accounts is $total, expected $expectedSum\n";
}
my $travg = $trtime / $trcount;
printf 'The %d transactions took %.2fs total, or an average of %.4fs each'."\n",
	$trcount, $trtime, $travg;
my $balavg = $baltime / $balcount;
printf 'The %d balance checks took %.2fs total, or an average of %.4fs each'."\n",
	$balcount, $baltime, $balavg;

