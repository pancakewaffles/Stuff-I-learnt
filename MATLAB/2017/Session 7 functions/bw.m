% function bw -> returns the free energy
function result = bw(q,Tc,T)
    result = -1 .* Tc .* q.^2 + ...
        T .* ( (1+q).*log(1+q) + ...
        (1-q).*log(1-q) );
end
    