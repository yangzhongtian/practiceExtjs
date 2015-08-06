Ext.onReady(function(){
	Ext.define('funs',{
		extend:'Ext.data.Model',
		fields:[{            // 注意格式     [{},{}]
			name:'id'
			},{ 
			name:'name'
			},{
			name:'url'
			},{
			name:'parentId'
		}]
	});
	var gridStore = Ext.create("Ext.data.Store",{
		model:'funs',
		proxy:{
			type: 'ajax',
		        url:'setting/functionAction_getFun',
		        reader: {
		            type: 'json'
		           
		           
		        }
		},
//		fields:[                        //不用model 时 
//			{name:"id"},
//			{name:"name"},
//			{name:"url"},
//			{name:'parentId'}
//			
//		],
		autoLoad:true
	})
	
	var  grid = Ext.create("Ext.grid.Panel",{
		title:'功能列表',
		store:gridStore,
		width:550,
		tbar:[{
			text:'添加',
			handler:function(){
				window.show();
				
			}
		},{
			text:'修改'
		},{
			text:'删除'
		}],
		columns:[{
			text:'序号',xtype:'rownumberer',width:50
		},{
			text:"功能名称",dataIndex:'name',width:150
		},{
			text:"功能地址",dataIndex:'url',width:250
		},{
			text:"所属节点",dataIndex:'parentId',width:80
		}],
		renderTo:Ext.getBody()
	})
	
	var window = new Ext.create("Ext.window.Window",{
		title:"添加窗口",
		x:400,
		y:50,
		width:300,
		height:300,
		layout:'fit',
		closeAction:'hide',
		renderTo:Ext.getBody(),
		items:[{
			xtype:'form',
			frame:true,
			url:'setting/functionAction_addFun',
			laylout:'anchor',
			defaults:{
				xtype:'textfield'
			},
			items:[{
				fieldLabel:'功能名称',
				name:'name',
				anchor:'100%',
				allowBlank:false
			},{
				fieldLabel:"功能地址(url)",
				name:'url',
				anchor:'100%',
				allowBlank:false
			},{
				fieldLabel:'父id',
				name:'parentId'
				
			}],
			buttons:[{
				text:'reset',
				handler:function(){
				
				}
			},{
				text:'提交',
				handler:function(){
					form =this.up('form').getForm();
					if(form.isValid()){
						form.submit({
						  success: function(form, action) {
	                       Ext.Msg.alert('Success', action.result.msg);
	                       window.close();
	                       gridStore.load();
	                    },
	                    failure: function(form, action) {
	                        Ext.Msg.alert('Failed', action.result.msg);
	                    }})
						
						
					}
				}
			}]
		}]
	})
	
});