  Ext.define("Ext.ux.FieldChannelPanel",{
    	 extend:'Ext.panel.Panel',
    	 alias:'uxFC',
    	//初始化组件
    	 initComponent: function(config) {
       		 var me = this;
       		 me.fieldWin = me.createWin(); 
       		 me.channelWin  = me.createChannelWin();
       		 Ext.apply(me, config);
       		 me.layout='column';
       		 me.items=[{                            //领域  与  客户频道  radio
       			  xtype: 'radiogroup',
	                id:'fieldChannel',
	                columns: 2,
	                defaults: {
	                    name: 'fieldChannel' 
	                },
	                items: [{
		                    inputValue: 'field',
		                    boxLabel: '领域',
		                    checked:true
		                    
		                }, {
		                    inputValue: 'channel',           
		                    boxLabel: '频道'
		                }]
       		 },{
       			xtype:'button',                                    //选择按钮   通过radio 选择的值   弹出 领域或  频道的窗口   
				text:'请选择',
				handler:function(){
	       			   var fcRadio = Ext.getCmp('fieldChannel');
				       var flag= fcRadio.getValue().fieldChannel;
						
						if(flag=="field"){
							me.fieldWin.show();
						}else{
						
							me.channelWin.show();
						}
					}
				}
       		];
       		 
       		this.callParent(); 
        },
		/*返回领域窗口        
		 * @memberOf {TypeName} 
		 * @return {TypeName} 
		 */
      createWin: function (){
        	var fieldItems=[];
        	var me =this;
        	Ext.Ajax.request({
        		 url:'webview/contentSearch_innitMenu',
        		 async:false,
        		 params:{},
        		 success:function(response){     
					    eval("var fieldArr ="+response.responseText);
						var temp;
						for(var i=0;i<fieldArr.length;i++){
							temp ={
								boxLabel  : fieldArr[i].name,
			                  	 name:'field',
			                    inputValue: fieldArr[i].id,
			                    id        :'field'+ fieldArr[i].id
							}
							fieldItems.push(temp);
						}  
				    }  ,
				     error:function(){     
				       Ext.Msg.alert('提示框','请求领域信息失败');
				    }  
        	});

        	var checkGroup2 ={
			    xtype: "checkboxgroup",
			    fieldLabel: "领域",
			    id:'checkBoxgroup',
			    vertical: true,
			    columns: 1,
			    flex: 1,
			    items:fieldItems
			}
        	var Win = Ext.create("Ext.window.Window",{
        		title:'选择领域',
				width:300,
				modal:true,
				frame:true,
				closeAction:'hide',
				items:[{xtype : "tbfill"
				},{
					xtype:'button',	
		            text:'全选',
		            style:{
		            	marginLeft:80
		            },
		            handler:function(){
		            	var checkBoxItems = Ext.getCmp('checkBoxgroup').items;
		            	  checkBoxItems.each(function(item){
                           item.setValue(true);  
                       });  
		            }
				},{
					xtype:'button',	
		            text:'取消',
		             style:{
		            	marginLeft:30
		            },
		            handler:function(){
		            	var checkBoxItems = Ext.getCmp('checkBoxgroup').items;
		            	
		            	  checkBoxItems.each(function(item){  
                            item.setValue(false);  
                        });  
		            }
				
				},checkGroup2],
				
				buttons:[{
					xtype:'button',
		            text:'确定',
		            handler:function(){
		            	me.fieldWin.hide();
		           }
	       		}],
				renderTo:Ext.getBody()
        	});
        	return Win;
        },
        
       /*
        * 创建 客户频道窗口
        * @memberOf {TypeName} 
        * @return {TypeName} 
        */
        createChannelWin:function(){
        	var me = this;
        		var  uxtreeStore = Ext.create('Ext.data.TreeStore',{
				proxy:{
					type:'ajax',
					url:'webview/contentSearch_innitChannel',
					extraParams:{
						parentId:''
					}
				},
				 listeners : {  
		            'beforeexpand' : function(node,eOpts){  
		        //点击父亲节点的菜单会将节点的id通过ajax请求，将到后台  
		                this.proxy.extraParams.parentId = node.raw.id;  
		            }  
		        }  
			});
			
	//频道窗口中的树		
  	   uxtree = Ext.create("Ext.tree.Panel",{
       	id:"ptree",
  	 	store : uxtreeStore,
        rootVisible: false,
        height:480,
        autoScroll:true,
        listeners:{
  		   checkchange:function (node, checked, eOpts){
  		   		
  		   		checkChildNode(node,checked);
  		   }
        }
  	 });
		
	
		//树形节点全选
		function checkChildNode(node,checked) {
           //设置某个节点的checkbox的选中状态
          if(node.data.checked!=null){
           		node.set("checked", checked);
           		
          	 }
           node.eachChild(function (child) {
               checkChildNode(child ,checked);
           })
          
       };
       
      
      	 //频道窗口
		var channelWin =	Ext.create('Ext.window.Window',{
				title:'选择频道',
				width:280,
				y:20,
				//height:550,
				modal:true,
				autoHeight:true,
				closeAction:'hide',
				draggable:true,
				tbar:[{xtype : "tbfill"
				},{
					xtype:'button',	
		            text:'全选',
		            handler:function() {  
                          rec = uxtree.getRootNode();
                          	checkChildNode(rec,true);
                     }  
				},{
					xtype:'button',	
		            text:'取消',
		            handler:function(){
	                   rec = uxtree.getChecked();
	                	for(var i=0;i<rec.length;i++){
	                		rec[i].set("checked",false);
	                	}
	                }
	              },{
	               xtype: 'tbfill'  
				}],
				items:[uxtree],
				buttons: [
			       {
					xtype:'button',	
		            text:'确定',
		            handler:function(){
		            	me.channelWin.hide();
		           }
				}
			    ],
				renderTo:Ext.getBody()
			});
	
			return channelWin;
        },
		
        /**
         * 对外方法 通过获取组件调用此方法 获得选中的值 {flag: "Channel", ids: Array[1,3]}
         * flag 值 对应选择是领域还是频道：Field      Channel  
         * ids  选中的id 数组
         * @return {TypeName} 
         */
        getSelectValue: function(){
        			var arr=[];
					 var fcRadio = Ext.getCmp('fieldChannel');
				      var flag= fcRadio.getValue().fieldChannel;
						
						
					if(flag=="field"){
						flag2='Field';
						var items =Ext.getCmp('checkBoxgroup').getChecked();
						for(var i=0;i<items.length;i++){
							arr.push(items[i].inputValue)
						}
					}else{
						flag2 = 'Channel';
						var records = uxtree.getView().getChecked();
	                    Ext.Array.each(records, function(rec){
	                    	 if(rec.get('id').indexOf('c')==0){
	                       		 arr.push(rec.get('id').replace(/c/,""));
	                        }
	                    });
					};
					
					return {flag:flag2 , ids:arr};
        }
       
     })