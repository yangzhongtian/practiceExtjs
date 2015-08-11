Ext.onReady(function(){
	var treeStore = new Ext.data.TreeStore({
		model: 'function',
        id:'treeStore',
        proxy: {
            type: 'ajax',
          //  timeout:1000*90,
            getMethod: function(){ return 'POST'; },
            url: 'setting/functionAction_getTree',
            extraParams:{
				parentId:0
			}
        },
        listeners:{
			'beforeexpand' : function(node,eOpts){  
		        //点击父亲节点的菜单会将节点的id通过ajax请求，将到后台  
		                this.proxy.extraParams.parentId = node.raw.id;  
		       }  
		      
		}
	})
	
	var tree = Ext.create('Ext.tree.Panel',{
		title:'功能菜单',
		store:treeStore,
		rootVisible:false,
		listeners:{
			'itemclick': function( thi, record){
				
		      	var panel = Ext.create("Ext.panel.Panel",{
		      			title:record.raw.text,
		      			closable:true,
		      			html:'<iframe id="amFrame" name="amFrame" src="'+record.raw.url+'" width="100%" height="100%" frameborder="0"></iframe>',
		      			id:"ta"+record.raw.id
		      		});
		      	    var id ="ta"+record.raw.id;
		      		
		      		
		      		
		      		Ext.getCmp('centerPanel').add(panel);
		      		Ext.getCmp('centerPanel').setActiveTab(id);
		      }
		}
		
	})
	var views  = Ext.create('Ext.container.Viewport',{
		layout:'border',
		frame:true ,
		defaults:{
			frame:true
		},
		items:[{
			boder:false,
			html:'<h1 >Extjs</h1>',
			style:{
				textAlign:'center'
			},
			region:'north',
			height:50
		},{
			region:'west',
			title:'菜单栏',
			width:200,
			layout:'fit',
			items:[tree],
			collapsible:true
		},{
			region:'center',
			xtype:'tabpanel',
			id:'centerPanel',
			
			items:[{
				title:'功能设置',
				
				html:'<iframe id="amFrame" name="amFrame" src="pages/setting/function.jsp" width="100%" height="100%" frameborder="0"></iframe>'
			}]
		}],
		renderTo:Ext.getBody()
	})
});