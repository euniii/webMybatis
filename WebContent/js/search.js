//�׸�, ���� ����
function doChange(srcE, targetId){
    var val = srcE.options[srcE.selectedIndex].value;
    var targetE = document.getElementById(targetId);
    removeAll(targetE);

    if(val == '1'){	//����
        addOption('�ĺ�', targetE);
        addOption('�����', targetE);
        addOption('��ź�', targetE);
        addOption('��Ȱ��ǰ��', targetE);
        addOption('��ȭ��Ȱ��', targetE);
        addOption('�ǰ��Ƿ�', targetE);
        addOption('�̿��Ƿ�', targetE);
    }
    else if(val == '2'){ //����
        addOption('�޿�', targetE);
        addOption('���ڼ���', targetE);
        addOption('��Ÿ', targetE);
    }
}

function addOption(value, e){
    var o = new Option(value);
    try{
        e.add(o);
    }catch(ee){
        e.add(o, null);
    }
}

function removeAll(e){
    for(var i = 0, limit = e.options.length; i < limit - 1; ++i){
        e.remove(1);
    }
}