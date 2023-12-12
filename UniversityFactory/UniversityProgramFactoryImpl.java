package a02b.e1;

import java.util.Set;

public class UniversityProgramFactoryImpl implements UniversityProgramFactory {

    @Override
    public UniversityProgram flexible() {
        return new AbstractUniversityProgram() {

            @Override
            public boolean isValid(Set<String> courseNames) {
                int tot = getTotalCredits(courseNames);
                return tot == 60;
            }
            
        };
    }

    @Override
    public UniversityProgram scientific() {
        return new AbstractUniversityProgram() {

            @Override
            public boolean isValid(Set<String> courseNames) {
                int tot = getTotalCredits(courseNames);
                return tot == 60 && getMath() >= 12 && getCs() >= 12 && getPhysics() >= 12;
            }
  
        };
    }

    @Override
    public UniversityProgram shortComputerScience() {
        return new AbstractUniversityProgram() {

            @Override
            public boolean isValid(Set<String> courseNames) {
                int tot = getTotalCredits(courseNames);
                return tot >= 48 && getCe() + getCs() >= 30;
            }
            
        };
    }

    @Override
    public UniversityProgram realistic() {
        return new AbstractUniversityProgram() {

            @Override
            public boolean isValid(Set<String> courseNames) {
                int tot= getTotalCredits(courseNames);
                return tot == 120 && getCe() + getCs() >= 60 && getMath() + getPhysics() <= 18 && getThesis() == 24;
            }
            
        };
    }

}
