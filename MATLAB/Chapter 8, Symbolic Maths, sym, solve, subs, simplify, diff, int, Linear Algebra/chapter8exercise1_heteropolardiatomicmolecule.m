% chapter8exercise1_heteropolardiatomicmolecule.m

% A very simple model for the first two electron energy levels in a
% heteropolar diatomic molecule defines them as the values of epsilon that are
% solution of the following equation:

%          | e1 - epsilon     -t      | 
%          |     -t       e2 - epsilon|   = 0
% where e1 , e2 and t are positive energy parameters. Use symbolic algebra
% find the two solutions of that equation as a function of those three
% parameters.

% Well, isn't it just the equation det(matrix) = 0? It's a matrix without
% inverse if you are interested. :)

mymat = [sym('e1 - epsilon') , sym('-t'); ...
         sym('-t')           , sym('e2-epsilon')];

myeqn = det(mymat);  
pretty(solve(myeqn,'epsilon')) % solves myeqn = 0. Wait. What if the question asks me to solve det(mymat) = 5 ?
                                                %Then you change myeqn =
                                                %det(mymat); to myeqn =
                                                %det(mymat) -5;
                                                
% e = (e1 + e2)/2 
% delta =  (e1 - e2)/2   

% Reexpress the solutions in terms of e and delta.
solution = solve(myeqn,'epsilon'); 
solution1 = subs(solution(1), sym('e1^2 - 2*e1*e2 +e2^2'), simplify(sym('e1^2 - 2*e1*e2 +e2^2')) );
solution1 = subs( solution1 , [ sym('(e1+e2)/2') , sym('(e1-e2)')]  , [ sym('e') , 2*sym('delta')] )

% Whew, had to do some preprocessing there!
% Now all we have to do is:
simplified_solution1 = simplify(solution1)

solution2 = subs(solution(2), sym('e1^2 - 2*e1*e2 +e2^2'), simplify(sym('e1^2 - 2*e1*e2 +e2^2')) );
solution2 = subs( solution2 , [ sym('(e1+e2)/2') , sym('(e1-e2)')]  , [ sym('e') , 2*sym('delta')] )
simplified_solution2 = simplify(solution2)








