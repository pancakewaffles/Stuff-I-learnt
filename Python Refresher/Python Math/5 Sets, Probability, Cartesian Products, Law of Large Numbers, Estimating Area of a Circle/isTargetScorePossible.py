#! isTargetScorePossible.py
# Tells us whether a certain target score is reachable within a maximum number of rolls.

from sympy import FiniteSet;
import random;

def find_prob(target_score,max_rolls):
    die_sides = FiniteSet(1,2,3,4,5,6);
    # Sample space
    s = die_sides**max_rolls # {1,2,3,4,5,6} x max_rolls {1,2,3,4,5,6}

    if(max_rolls>1):   
        success_rolls = [];
        for elem in s:
            if sum(elem) >= target_score:  
                success_rolls.append(elem);
                '''
                If you are wondering why we are not including the cases
                where we reach the target score before the max number of rolls,
                why, we actually do include them.
                If you reach the target score in 3 rolls out of a max of 6,
                then the next 3 rolls will simply give you a score > target_score.

                Any set in s that has a sum > target_score necessarily includes cases
                in which you reach the target_score before the maximum number of rolls.
                               
                '''

    else: # max_rolls = 1
        if(target_score>6):
            success_rolls = [];
        else:
            success_rolls = [];
            for roll in die_sides:
                if(roll >= target_score):
                    success_rolls.append(roll);

    e = FiniteSet(*success_rolls); # This is how you create a finite set from a list.

    # Calculate the probability of reaching target score
    return len(e)/len(s); # This is how you calculate probability using sets.
    # Number of elements in event set / number of elements in sample set

if(__name__=="__main__"):
    target_score = int(input("Enter the target score: "));
    max_rolls = int(input("Enter the maximum number of rolls allowed: "));
    p = find_prob(target_score,max_rolls);
    print("Probability: {0:.5f}".format(p));
