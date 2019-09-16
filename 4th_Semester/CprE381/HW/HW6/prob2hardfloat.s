#Code uses the hardware multiplier

# Data segment address of image low 0x0000, image high 0x1001
# Data segment address of output low 0x0090, high 0x1001
# Data segment address of kernel low 0x00D0, high 0x1001


.data
Image:
        .float   10.0
        .float   10.0
        .float   10.0
        .float   0
        .float   0
        .float   0
        .float   10.0
        .float   10.0
        .float   10.0
        .float   0
        .float   0
        .float   0
        .float   10.0
        .float   10.0
        .float   10.0
        .float   0
        .float   0
        .float   0
        .float   10.0
        .float   10.0
        .float   10.0
        .float   0
        .float   0
        .float   0
        .float   10.0
        .float   10.0
        .float   10.0
        .float   0
        .float   0
        .float   0
        .float   10.0
        .float   10.0
        .float   10.0
        .float   0
        .float   0
        .float   0
Output:
        .float   0.0
        .float   0.0
        .float   0.0
        .float   0.0
        .float   0.0
        .float   0.0
        .float   0.0
        .float   0.0
        .float   0.0
        .float   0.0
        .float   0.0
        .float   0.0
        .float   0.0
        .float   0.0
        .float   0.0
        .float   0.0
kernel:
        .float   1.0
        .float   0.0
        .float   -1.0
        .float   1.0
        .float   0
        .float   -1.0
        .float   1.0
        .float   0.0
        .float   -1.0
        
.text
main:
		
		li		$9, $0, 1		# PUT HERE BY ME FOR TESTING
		



        addiu   $sp,$sp,-32
        sw      $fp,28($sp)
        move    $fp,$sp
        sw      $0,8($fp)
$L15:
        lw      $2,8($fp)
        nop
        slti     $2,$2,4
        beq     $2,$0,$L8
        nop

        sw      $0,12($fp)
$L14:
        lw      $2,12($fp)
        nop
        slti     $2,$2,4
        beq     $2,$0,$L9
        nop

        lw      $2,8($fp)
        nop
        sw      $2,16($fp)
$L13:
        lw      $2,8($fp)
        nop
        addiu   $3,$2,3
        lw      $2,16($fp)
        nop
        slt     $2,$2,$3
        beq     $2,$0,$L10
        nop

        lw      $2,12($fp)
        nop
        sw      $2,20($fp)
$L12:
        lw      $2,12($fp)
        nop
        addiu   $3,$2,3
        lw      $2,20($fp)
        nop
        slt     $2,$2,$3
        beq     $2,$0,$L11
        nop

        lui     $2,0x1001
        lw      $3,8($fp)
        nop
        sll     $4,$3,2
        lw      $3,12($fp)
        nop
        addu    $3,$4,$3
        sll     $3,$3,2
        addiu   $2,$2,0x0090
        addu    $2,$3,$2
        lwc1    $f2,0($2)
        lui     $4,0x1001
        lw      $2,16($fp)
        nop
        move    $3,$2
        sll     $2,$3,1
        move    $3,$2
        sll     $2,$3,2
        subu    $2,$2,$3
        lw      $3,20($fp)
        nop
        addu    $2,$2,$3
        sll     $3,$2,2
        addiu   $2,$4,0x0000
        addu    $2,$3,$2
        lwc1    $f4,0($2)
        lw      $3,16($fp)
        lw      $2,8($fp)
        nop
        subu    $3,$3,$2
        lw      $4,20($fp)
        lw      $2,12($fp)
        nop
        subu    $5,$4,$2
        lui     $4,0x1001
        move    $2,$3
        sll     $2,$2,1
        addu    $2,$2,$3
        addu    $2,$2,$5
        sll     $3,$2,2
        addiu   $2,$4,0x00D0
        addu    $2,$3,$2
        lwc1    $f0,0($2)
        nop
		
		
        mul.s   $f0,$f4,$f0			#MUL
		
		li 		$v0, 2              #system call code for Print String
		move   		$a0, $9         #load address of message into $a0
		syscall
		
		
        add.s   $f0,$f2,$f0
        lui     $2,0x1001
        lw      $3,8($fp)
        nop
        sll     $4,$3,2
        lw      $3,12($fp)
        nop
        addu    $3,$4,$3
        sll     $3,$3,2
        addiu   $2,$2,0x0090
        addu    $2,$3,$2
        swc1    $f0,0($2)
        lw      $2,20($fp)
        nop
        addiu   $2,$2,1
        sw      $2,20($fp)
        b       $L12
        nop

$L11:
        lw      $2,16($fp)
        nop
        addiu   $2,$2,1
        sw      $2,16($fp)
        b       $L13
        nop

$L10:
        lw      $2,12($fp)
        nop
        addiu   $2,$2,1
        sw      $2,12($fp)
        b       $L14
        nop

$L9:
        lw      $2,8($fp)
        nop
        addiu   $2,$2,1
        sw      $2,8($fp)
        b       $L15
        nop

$L8:
        move    $2,$0
        move    $sp,$fp
        lw      $fp,28($sp)
        addiu   $sp,$sp,32
        li $v0, 10
    	syscall
        

        
multiply:
        addiu   $sp,$sp,-32
        sw      $fp,28($sp)
        move    $fp,$sp
        swc1    $f12,32($fp)
        swc1    $f14,36($fp)
        li      $2,1                        # 0x1
        lwc1    $f0,32($fp)
        mtc1    $0,$f2
        nop
        c.eq.s  $f0,$f2
        nop
        bc1t    $L2
        nop

        move    $2,$0
$L2:
        andi    $3,$2,0x00ff
        li      $2,1                        # 0x1
        lwc1    $f0,36($fp)
        mtc1    $0,$f2
        nop
        c.eq.s  $f0,$f2
        nop
        bc1t    $L3
        nop

        move    $2,$0
$L3:
        andi    $2,$2,0x00ff
        or      $2,$3,$2
        andi    $2,$2,0x00ff
        beq     $2,$0,$L4
        nop

        mtc1    $0,$f0
        b       $L6
        nop

$L4:
        lwc1    $f0,32($fp)
        nop
        swc1    $f0,8($fp)
        lwc1    $f0,36($fp)
        nop
        swc1    $f0,12($fp)
        lw      $2,8($fp)
        nop
        srl     $2,$2,1
        move    $3,$2
        li      $2,-1                 # 0xffffffffffffffff
        and     $2,$3,$2
        andi    $3,$2,0x00ff
        lw      $2,12($fp)
        nop
        srl     $2,$2,1
        move    $4,$2
        li      $2,-1                 # 0xffffffffffffffff
        and     $2,$4,$2
        andi    $2,$2,0x00ff
        addu    $2,$3,$2
        andi    $2,$2,0x00ff
        addiu   $2,$2,-127
        andi    $2,$2,0x00ff
        sll     $2,$2,1
        lw      $4,16($fp)
        li      $3,-511           # 0xfffffffffffffe01
        and     $3,$4,$3
        or      $2,$3,$2
        sw      $2,16($fp)
        lw      $2,8($fp)
        nop
        srl     $2,$2,9
        sll     $2,$2,9
        lw      $3,16($fp)
        nop
        andi    $3,$3,0x1ff
        or      $2,$3,$2
        sw      $2,16($fp)
        lw      $2,8($fp)
        nop
        andi    $2,$2,0x1
        andi    $3,$2,0x00ff
        lw      $2,12($fp)
        nop
        andi    $2,$2,0x1
        andi    $2,$2,0x00ff
        xor     $2,$3,$2
        andi    $2,$2,0x00ff
        andi    $2,$2,0x1
        lw      $4,16($fp)
        li      $3,-2                 # 0xfffffffffffffffe
        and     $3,$4,$3
        or      $2,$3,$2
        sw      $2,16($fp)
        lwc1    $f0,16($fp)
$L6:
        move    $sp,$fp
        lw      $fp,28($sp)
        addiu   $sp,$sp,32
        jr       $31
        nop

