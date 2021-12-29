public class timetables {
    private String TID;
    private String Subject;
    private String Day;
    private String sessionTime;

    public timetables(String TID, String subject, String day, String sessionTime) {
        this.TID = TID;
        Subject = subject;
        Day = day;
        this.sessionTime = sessionTime;
    }

    public String getTID() {
        return TID;
    }

    public String getSubject() {
        return Subject;
    }

    public String getDay() {
        return Day;
    }

    public String getSessionTime() {
        return sessionTime;
    }
}
