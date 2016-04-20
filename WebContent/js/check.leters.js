
function proverka(input) { 
    var value = input.value; 
    var rep = /[-\.;":'a-zA-Zа-яА-Я]/; 

     if (rep.test(value)) { 
        value = value.replace(rep, ''); 
        
    } 
    input.value = value; 
} 
