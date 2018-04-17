package com.udacity.gamedev.dragoncurve;

import com.badlogic.gdx.math.Vector2;

import java.util.LinkedList;

public class DragonCurveGenerator {

    enum Direction {
        LEFT,
        RIGHT;

        public static Vector2 turn(Vector2 heading, Direction turn){
            Vector2 newHeading = new Vector2();
            switch (turn){
                case LEFT:
                    newHeading.x = -heading.y;
                    newHeading.y = heading.x;
                    break;
                case RIGHT:
                    newHeading.x = heading.y;
                    newHeading.y = -heading.x;
            }
            return newHeading;
        }
    }

    static LinkedList<Direction> dragonTurns(int recursions){
        LinkedList<Direction> turns = new LinkedList<Direction>();
        turns.add(Direction.LEFT);

        for (int i = 0; i < recursions; i++){
            // TODO: Create a reversed copy of turns
            LinkedList<Direction> reversed = new LinkedList<Direction>();
            for(int j=0;j<turns.size();j++){
                int last = turns.size() - 1 - j;
                reversed.add(turns.get(last));
            }

            // TODO: Add a left turn to turns
            turns.add(Direction.LEFT);

            // TODO: Add reflected version of reversed to turns
            for(int j = 0;j<reversed.size();j++){
                if(reversed.get(j) == Direction.LEFT){
                    turns.add(Direction.RIGHT);
                }
                if(reversed.get(j) == Direction.RIGHT){
                    turns.add(Direction.LEFT);
                }
            }
        }
        return turns;
    }

    static float[] generateDragonCurve(int width, int height, int recursions){

        LinkedList<Direction> turns = DragonCurveGenerator.dragonTurns(recursions);

        Vector2 head = new Vector2(width/2, height/2);
        Vector2 heading = new Vector2(5, 0);

        float[] curve = new float[(turns.size() + 1) * 2];

        int i = 0;
        curve[i++] = head.x;
        curve[i++] = head.y;

        //TODO: Convert the list of turns into the actual path
        // i = 2 now
        for(int j = 0;j<turns.size();j++){
            Direction t = turns.get(j);
            heading = t.turn(heading,t);
            //Debug System.out.println(heading.x + " "+heading.y);
               // | Here, curve[2] gets set.
                                     // | Then i increments by 1 to become 3 here.
            curve[i++] = heading.x + curve[i-3];
            curve[i++] = heading.y + curve[i-3];



        }


        return curve;

    }


}
