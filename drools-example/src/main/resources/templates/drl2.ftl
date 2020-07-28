<#list 0..100 as i>
rule "${prefix}__${i}"
    when
        $d :Double(this == ${i})
    then
        System.err.println("${prefix}__${i} " + $d);
end

</#list>