class Calculation {
    public static double calculateCalorie(int age, double weight, double height, boolean gender, double sport)
    {
        double result = -1;
        double BMR;

        //This is Revised Harris-Benedict Equation
        if (gender) {
            BMR = 88.362 + (13.397 * weight) + (4.799 * height) - (5.677 * age);
            result = BMR * sport;
        } else {
            BMR = 447.593 + (9.247 * weight) + (3.098 * height) - (4.330 * age);
            result = BMR * sport;
        }
        return result;
    }
}
