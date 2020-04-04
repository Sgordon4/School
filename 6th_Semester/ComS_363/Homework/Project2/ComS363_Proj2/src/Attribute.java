public class Attribute {
    String name;
    int type;

    public Attribute(String name, int type){
        this.name = name;
        this.type = type;
    }

    public String intToType(int type){
        switch (type){
            case 1: return "Integer";
            case 2: return "double";
            case 3: return "boolean";
            case 4: return "String";
            default: return null;
        }
    }

    /**
     * Ensures the user input is the same type as is required
     * @param input User input
     * @param type The attribute type that must be matched
     * @return true for correct, false for incorrect
     */
    public boolean checkCorrectType(String input, int type){

        try{
            switch(type) {
                case 1:
                    Integer.parseInt(input);
                    break;

                case 2:
                    Double.parseDouble(input);
                    break;

                case 3:
                    if(!(input.equals("T") || input.equals("F")))
                        throw new NumberFormatException();
                    break;
            }

        }catch (NumberFormatException e) {
            System.out.println(input+" is not of type "+intToType(type));
            return false;
        }

        return true;
    }
}
