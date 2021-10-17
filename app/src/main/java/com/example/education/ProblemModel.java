package com.example.education;

public class ProblemModel {
    String Problem;
    String Number;

    public ProblemModel() {
    }

    public String getProblem() {
        return Problem;
    }

    public void setProblem(String problem) {
        Problem = problem;
    }

    public String getNumber() {
        return Number;
    }

    public void setNumber(String number) {
        Number = number;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public ProblemModel(String problem, String number, String name) {
        Problem = problem;
        Number = number;
        Name = name;
    }

    String Name;


}
