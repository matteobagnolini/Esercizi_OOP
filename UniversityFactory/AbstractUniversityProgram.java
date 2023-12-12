package a02b.e1;

import java.util.*;

import a02b.e1.UniversityProgram.Sector;

public abstract class AbstractUniversityProgram implements UniversityProgram {

    Map<String, Course> courses = new HashMap<>();

    private int cs = 0;
    private int ce = 0;
    private int math = 0;
    private int physics = 0;
    private int thesis = 0;


    @Override
    public void addCourse(String name, Sector sector, int credits) {
        courses.put(name, new Course(name, sector, credits));
    }

    @Override
    public abstract boolean isValid(Set<String> courseNames);

    protected int getTotalCredits(Set<String> courseNames) {
        cs = ce = math = physics = thesis = 0;
        for(var name : courseNames) {
            Course course = courses.get(name);
            switch(course.sector()) {
            case COMPUTER_SCIENCE: cs += course.credits();
                break;
            case COMPUTER_ENGINEERING: ce += course.credits();
                break;
            case MATHEMATICS: math += course.credits();
                break;
            case PHYSICS: physics += course.credits();
                break;
            case THESIS: thesis += course.credits();
                break;
        }
        }
        return cs + ce + math + physics + thesis;
    }

    protected int getCe() {
        return ce;
    }

    protected int getCs() {
        return cs;
    }

    protected int getMath() {
        return math;
    }

    protected int getPhysics() {
        return physics;
    }

    protected int getThesis() {
        return thesis;
    }

}
