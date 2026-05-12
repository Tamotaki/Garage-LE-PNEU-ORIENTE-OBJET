package model;

public enum TypePermis {
    B, C, CE;

    public boolean couvre(TypePermis requis) {
        if (requis == B) return true;
        if (requis == C) return this == C || this == CE;
        if (requis == CE) return this == CE;
        return false;
}
