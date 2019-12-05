package statistics;

import statistics.matcher.*;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class QueryBuilder {

    private ArrayList<Matcher> matchers;
    private Matcher matcher;

    public QueryBuilder(){
        this.matcher = new All();
    }

    public Matcher build(){
        Matcher m = this.matcher;
        this.matcher = new All();

        return m;

    }

    public QueryBuilder playsIn(String team) {
        this.matcher = new And(this.matcher, new PlaysIn(team));
        return this;
    }

    public QueryBuilder hasFewerThan(int value, String field){
        this.matcher = new And(this.matcher, new HasFewerThan(value, field));
        return this;
    }

    public QueryBuilder hasAtLeast(int value, String field) {
        this.matcher = new And(this.matcher, new HasAtLeast(value, field));
        return this;
    }

    public QueryBuilder oneOf(Matcher m1, Matcher m2) {
        this.matcher = new And(this.matcher, new Or(m1, m2));
        return this;
    }



}
