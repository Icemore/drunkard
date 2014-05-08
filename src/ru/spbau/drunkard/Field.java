package ru.spbau.drunkard;

import ru.spbau.drunkard.actors.Actor;

import java.util.*;

public class Field {
    public static final int dx[] = {1, 0, -1, 0};
    public static final int dy[] = {0, 1, 0, -1};
    public final int width = 15;
    public final int height = 15;
    private Map<Position, Actor> actors;

    public Field() {
        this.actors = new HashMap<>();
    }

    public Collection<Actor> getActors() {
        return actors.values();
    }

    public void addActor(Actor actor) {
        actors.put(actor.getPos(), actor);
    }

    public void addActor(Actor actor, Position pos) {
        actor.setPos(pos);
        addActor(actor);
    }

    public void removeActor(Actor actor) {
        removeActor(actor.getPos());
    }

    public void removeActor(Position pos) {
        actors.remove(pos);
    }

    public Actor getOnPos(Position pos) {
        return actors.get(pos);
    }

    public void moveActor(Actor actor, Position to) {
        if (to == null) return;

        Position from = actor.getPos();

        if (actors.containsKey(to)) {
            Actor obstacle = actors.get(to);
            obstacle.acceptVisitor(actor);
        } else {
            if (isValidPos(to)) {
                actors.remove(from);
                actors.put(to, actor);
                actor.setPos(to);
                actor.afterMove(from);
            }
        }
    }

    public boolean isValidPos(Position pos) {
        return pos.x >= 0 && pos.y >= 0 && pos.x < width && pos.y < height;
    }

    public Position findFirstStepInPath(Position start, Position finish) {
        Queue<Position> queue = new ArrayDeque<>();
        Set<Position> used = new HashSet<>();

        queue.add(finish);
        used.add(finish);

        while (!queue.isEmpty()) {
            Position cur = queue.poll();

            for (int direction = 0; direction < 4; direction++) {
                Position to = new Position(cur.x + dx[direction], cur.y + dy[direction]);

                if (to.equals(start)) {
                    return cur;
                }

                if (!isValidPos(to) || used.contains(to) || actors.containsKey(to)) continue;

                queue.add(to);
                used.add(to);
            }
        }

        return null;
    }

    public List<Actor> findActor(Class actorClass) {
        List<Actor> result = new ArrayList<>();

        for (Actor actor : getActors()) {
            if (actor.getClass() == actorClass) {
                result.add(actor);
            }
        }

        return result;
    }

    public List<Actor> findActor(Class actorClass, Position refPoint, int maxDist) {
        List<Actor> result = new ArrayList<>();

        for (Actor actor : findActor(actorClass)) {
            if (actor.getPos().dist(refPoint) <= maxDist) {
                result.add(actor);
            }
        }

        return result;
    }
}
