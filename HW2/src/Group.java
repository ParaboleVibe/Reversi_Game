public class Group {
    private final Student[] listOfStudents;
    private int countOfInterviewedStudents;

    Group() {
        listOfStudents = new Student[]{
                new Student("Амирханов Никита Русланович"),
                new Student("Артемов Никита Владиславович"),
                new Student("Барков Борис Геннадьевич"),
                new Student("Бесшапов Алексей Павлович"),
                new Student("Боссерт Александра Максимовна"),
                new Student("Гладких Иван Дмитриевич")
        };
        countOfInterviewedStudents = 0;
    }

    public Student ChooseLuckyStudent() {
        if (countOfInterviewedStudents == listOfStudents.length) {
            throw new IndexOutOfBoundsException("""
                    Все студенты уже получили оценки.
                    Type "/l" to see the list of students
                     or "/s" to stop.
                    """);
        } else {
            int num;
            do {
                num = (int) (Math.random() * listOfStudents.length);
            } while (listOfStudents[num].getState() != 0);
            ++countOfInterviewedStudents;
            return listOfStudents[num];
        }
    }

    public int getCountOfStudents() {
        return  listOfStudents.length;
    }

    @Override
    public String toString() {
        StringBuilder output = new StringBuilder();
        for (var st :
                listOfStudents) {
            output.append(st.toString()).append("\n");
        }
        return output.toString();
    }
}
