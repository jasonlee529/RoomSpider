<#list 1..5000 as i>
rule "b${i}"
    when
        $d :Double(this == ${i})
    then
        System.err.println("b${i} " + $d);
end

</#list>