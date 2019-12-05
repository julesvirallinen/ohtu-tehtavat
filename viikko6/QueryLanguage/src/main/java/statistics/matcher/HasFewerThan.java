package statistics.matcher;

import statistics.Player;

public class HasFewerThan implements Matcher {

    private Not matcher;

    public HasFewerThan(int value, String field) {
        this.matcher = new Not(new HasAtLeast(value, field));
    }

    @Override
    public boolean matches(Player p) {
        return matcher.matches(p);
    }
}
