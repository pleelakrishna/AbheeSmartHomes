<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<style>
	#0>.fa-trash {
		display:none;
	}
</style>
	<div class="clearfix"></div>
	<ol class="breadcrumb">
		<li><a href="dashBoard">Home</a></li>
		<li>Product Master</li>
	</ol>
	<div class="clearfix"></div>
	<div class="container">
		<div class="row">
			<div class="col-md-12" style="background-color: white !important; padding-top: 15PX;">
				<div class="panel panel-primary">
					<div class="panel-heading">
						<h4>Product List</h4>
						<div class="options">
							<a href="javascript:;" class="panel-collapse"><i class="fa fa-chevron-down"></i></a>
						</div>
					</div>
					<div class="panel-body collapse in">
					<input type="checkbox" class="form-check-input" onclick="inactiveData();" id="inActive"> <label class="form-check-label">Show Inactive List</label>
						<div class="table-responsive" id="tableId">
							<table cellpadding="0" cellspacing="0" border="0" class="table table-striped table-bordered datatables" id="example">
								<thead><tr><th>Product ID</th><th>Name</th><th>Description</th><th>Status</th><th></th></tr></thead>
								<tbody></tbody>
							</table>
						</div>
					</div>
				</div>
			</div>
		</div>
<!-- 		<a class="btn btn-info btn-lg"  onclick="PopupFillingStation();">Add Gas</a><br><br> -->
		<div class="row" id="moveTo">
			<div class="col-md-12 col-sm-12">
				<div class="panel panel-primary">
					<div class="panel-heading">
						<h4 id="titleProduct">Add Product</h4>
					</div>
					<form:form class="form-horizontal" commandName="productf" role="form" id="fillingstation-form" action="product" method="post" enctype="multipart/form-data">
					<div class="panel-body">
						
                    	<div class="row">
                    		<div class="col-md-6">
                    			<div class="form-group">
                    			<form:hidden path="id"/>
									<label for="focusedinput" class="col-md-6 control-label">Product Category <span class="impColor">*</span></label>
									<div class="col-md-5">
									<form:select path="categoryid" class="form-control validate"  onfocus="removeBorder(this.id)">
											<form:option value="">-- Select Product Category --</form:option>
											<form:options items="${CategoriesMap}"/>
										</form:select>
										</div>
                    			</div>
                    		</div>
                    		<div class="col-md-6">
                    			<div class="form-group">
									<label for="focusedinput" class="col-md-6 control-label">Product Company <span class="impColor">*</span></label>
									<div class="col-md-5">
									<form:select path="companyid" id="companyid" class="form-control validate" onkeydown="removeBorder(this.id)" onfocus="removeBorder(this.id)">
											<form:option value="">-- Select Product Company --</form:option> 
											<form:options items="${companiesMap}"/>
										</form:select>
										</div>
                    			</div>
                    		</div>
                    		
                    		
                    	</div>
                    	<div class="row">
                    		<div class="col-md-6">
                    			<div class="form-group">
									<label for="focusedinput" class="col-md-6 control-label">Product Model<span class="impColor">*</span></label>
									<div class="col-md-5">
										<form:input path="name" class="form-control validate" placeholder="Product Model"/>	
										<span class="hasError" id="stationnameError"></span>
								    </div>
                    			</div>
                    		</div>
                    		
                    		
                    		<div class="col-md-6">
                    			<div class="form-group">
									<label for="focusedinput" class="col-md-6 control-label">Description <span class="impColor">*</span></label>
									<div class="col-md-5">
										<form:textarea path="description" class="form-control validate" placeholder="Product Model Description"/>	
										<span class="hasError" id="stationnameError"></span>
								    </div>
                    			</div>
                    		</div>
                    		</div>
                    		<div class="row">
                    		<div class="col-md-6">
                    			<div class="form-group">
									<label for="focusedinput" class="col-md-6 control-label">Specifications <span class="impColor">*</span></label>
									<div class="col-md-5">
										<form:textarea path="ProductModelSpecifications" class="form-control validate" placeholder="Product Model Specifications"/>	
										<span class="hasError" id="stationnameError"></span>
								    </div>
                    			</div>
                    		</div>
                    		
                    		<div class="col-md-6">
                    			<div class="form-group">
									<label for="focusedinput" class="col-md-6 control-label">Product Price<span class="impColor">*</span></label>
									<div class="col-md-5">
										<form:input path="ProductPrice" class="form-control validate numericOnly" placeholder="Product Price"/>	
										<span class="hasError" id="stationnameError"></span>
								    </div>
                    			</div>
                    		</div>
                    		</div>
                    		<div class="row">
                    		<div class="col-md-6">
                    			<div class="form-group">
									<label for="focusedinput" class="col-md-6 control-label">Maximum  Allowed Discount(%) <span class="impColor">*</span></label>
									<div class="col-md-5">
										<form:input path="maxAllowedDiscount" class="form-control validate numericOnly" maxlength="2" placeholder="Maximum  Allowed Discount"/>	
										<span class="hasError" id="stationnameError"></span>
								    </div>
                    			</div>
                    		</div>
                    		<div class="col-md-6">
                    			<div class="form-group" id="fileDiv">
									<label for="focusedinput" class="col-md-6 control-label">Product Images <span class="impColor">*</span></label>
									<div class="col-md-5">
										<input type="file" name="file1" id="file1" class="validate"  accept="image/*"  multiple="multiple" style="margin: 7px 0px 0px 0px;">
									</div>
                    			</div>
                    		</div>
                    		
                    		</div>
                    		
                    		
                    		
                    	
                    	<div class="row">
                    	<div class="col-md-6">
                    			<div class="form-group">
									<label for="focusedinput" class="col-md-6 control-label">VideosLinks Id(11Digits)<!-- <span class="impColor">*</span> --></label>
								    <div class="col-md-3" id ="dtext">
										<input type="text" id="name1" name='vlink' class="form-control " maxlength="11"  placeholder="Videolink Id"/>	
										<span class="hasError" id="stationnameError"></span>
								    </div> 
								    <div><input type="button" value="Add Another" onclick="addNewTextBox()"></div>
								    	<form:hidden path="productmodelvideoslinks"/>
                    			</div>
                    		</div>
                    		
                    		
                    	</div>
                    		
<!-- Modal Starts here-->
<!-- Modal Ends here-->

					</div>
					<div class="panel-footer">
				      	<div class="row">
				      		<div class="col-sm-12">
				      			<div class="btn-toolbar text-center">
					      			<input type="submit" id="submit1" value="Submit" class="btn-primary btn"/>
					      			<input type="reset" value="Reset" onClick="window.location.reload()" class="btn-danger btn cancel"/>
				      			</div>
				      		</div>
				      	</div>
			      	</div>
					</form:form>
				</div>
			</div>
		</div>
	</div>
			<!-- container -->


</body>
<%-- <script type='text/javascript' src='${baseurl }/js/custemValidation.js'></script>  --%>
<script>
</script>
<script type="text/javascript">

/* $(document).ready(function() {
	 var table = $('#example').DataTable();
	  
	 $('#example tbody').on('click', 'tr', function () {
	     var data = table.row( this ).data();
	     alert( 'You clicked on '+data[0]+'\'s row' );
	 } );
}); */

$(document).ready(function() {
	$('#categoryid').change(function() {
		var catid=$('#categoryid').val();
		console.log(catid);
		$.ajax({
			type : "POST",
			url : "products",
			data :'categoryid='+catid,
			dataType : "text",
			beforeSend : function() {
	             $.blockUI({ message: 'Please wait' });
	          }, 
			success : function(data) {
				var result = JSON.parse(data);
				var s= '';
				$('#companyid').empty();
				s= '<option value="" > -- Select Product Company --</option>';
				$('#companyid').append(s);
				for(var i=0;i<result.length;i++){
					s='<option value="'+result[i].id+'"> '+result[i].name+'</option>';
					$('#companyid').append(s);
				}
				
				
			},
			complete: function () {
	            
	            $.unblockUI();
	       },
			error :  function(e){$.unblockUI();console.log(e);}
			
		});
	});
});

var k =1;

var listOrders1 = ${allOrders1};
if (listOrders1 != "") {
	displayTable(listOrders1);
}
function displayTable(listOrders) {
	$('#tableId').html('');
	var tableHead = '<table id="product" class="table table-striped table-bordered datatables">'
			+ '<thead><tr><th>Product Category</th><th>Product Company</th><th>Product Model</th><th>Description</th><th>Specifications</th><th>Product Price</th><th>Maximum Allowed Discount</th><th>Product Model Videos Links</th><th>Product  Images</th><th style="text-align: center;">Options</th></tr></thead><tbody></tbody></table>';
	$('#tableId').html(tableHead);
	serviceUnitArray = {};
	$.each(listOrders,function(i, orderObj) {
		if(orderObj.productmodelpics==undefined) orderObj.productmodelpics='';
		else
			{
				var list=orderObj.productmodelpics.split('*');
				var productmodelpics='';
				for(var i=0;i<list.length;i++)
				{
					productmodelpics=productmodelpics+'<a href="../abheeimg/'+list[i]+'" target="_blank" title="'+list[i]+'"><img src="../abheeimg/'+list[i]+'" style="height:42px; width:42px"></a>';
				}
				orderObj.productmodelpics=productmodelpics;
			}
		if(orderObj.status == "1"){
			var deleterow = "<a class='deactivate' onclick='deleteProduct("+ orderObj.id+ ",0)'><i class='fa fa-eye'></i></a>"
		}else{  
			var deleterow = "<a class='activate' onclick='deleteProduct("+ orderObj.id+ ",1)'><i class='fa fa-eye-slash'></i></a>"
		}
		var edit = "<a class='edit editIt' onclick='editProduct("	+ orderObj.id+ ")'><i class='fa fa-edit'></i></a>"
		serviceUnitArray[orderObj.id] = orderObj;
		var tblRow = "<tr>"
			
			+ "<td title='"+orderObj.categoryname+"'>"+ orderObj.categoryname + "</td>"
			+ "<td title='"+orderObj.companyname+"'>"+ orderObj.companyname + "</td>"
			+ "<td title='"+orderObj.name+"'>"+ orderObj.name + "</td>"
			+ "<td title='"+orderObj.description+"'>"+ orderObj.description + "</td>"
			+ "<td title='"+orderObj.productModelSpecifications+"'>"+ orderObj.productModelSpecifications + "</td>"
			+ "<td title='"+orderObj.productPrice+"'>"+ orderObj.productPrice + "</td>"
			+ "<td title='"+orderObj.maxAllowedDiscount+"'>"+ orderObj.maxAllowedDiscount + "</td>"
			+ "<td title='"+orderObj.productmodelvideoslinks+"'>"+ orderObj.productmodelvideoslinks + "</td>"
			+ "<td title='"+orderObj.productmodelpics+"'>"+ orderObj.productmodelpics + "</td>"
			+ "<td style='text-align: center;white-space: nowrap;'>" + edit + "&nbsp;&nbsp;" + deleterow + "</td>" 
			+ "</tr>";
		$(tblRow).appendTo("#tableId table tbody");
	});
	if(isClick=='Yes') $('.datatables').dataTable();
	
}


function editProduct(id) {
	$('#titleProduct').text("Edit Product");
	
	$("#id").val(serviceUnitArray[id].id);
	$("#name").val(serviceUnitArray[id].name);
	$("#description").val(serviceUnitArray[id].description);
	$("#status").val(serviceUnitArray[id].status);
	$("#categoryid").val(serviceUnitArray[id].categoryid);
	/* $("#companyids").val(serviceUnitArray[id].companyname); */
	$("#companyid").empty();
	$("#companyid").append('<option value="'+serviceUnitArray[id].companyid+'" > '+serviceUnitArray[id].companyname+'</option>');
	$("#productmodelvideoslinks").val(serviceUnitArray[id].productmodelvideoslinks);
	$("#maxAllowedDiscount").val(serviceUnitArray[id].maxAllowedDiscount);
	$("#ProductPrice").val(serviceUnitArray[id].productPrice);
	$("#ProductModelSpecifications").val(serviceUnitArray[id].productModelSpecifications);
	$("#submit1").val("Update");
	$(window).scrollTop($('#moveTo').offset().top);
	
	$("#dtext").html("");
	    $('#name1').hide();
	    
	    var productmodelvideoslinks = $('#productmodelvideoslinks').val();
	    
	    var videosParts =productmodelvideoslinks.split('*');
        
        for(var i=0;i<videosParts.length;i++)
        	{
        	j=i+1;
        	
        	if(i==0){
        		
        		var row ="<div id="+i+"><input type='text' name='vlink' id='name"+j+"' value ='"+videosParts[i]+"' class='form-control validate' length='11' placeholder='Videos links'/></div>";
            	$("#dtext").append(row);
        	}else{
        	
        		k=k+1;
        	
        	var row ="<div id="+i+"><input type='text' name='vlink' id='name"+j+"' value ='"+videosParts[i]+"' class='form-control validate' length='11' placeholder='Videos links'/><a href='javascript:void(0);' style='color: red;' onclick='removeDependentRow("+i+");'><i class='fa fa-trash' style='color: red;text-decoration: none;cursor: pointer; float: right; margin-top:-25px; margin-right:-25px;'></i></a></div>";
        	$("#dtext").append(row);
        	
        	}
        	
        	}

}



    $('#editVideoslinks').hide();

function deleteProduct(id,status)
{
	var checkstr=null;
	if(status == 0){
		 checkstr = confirm('Are you sure you want to Deactivate?');
	}else{
		 checkstr = confirm('Are you sure you want to Activate?');
	}
	if(checkstr == true){
		var formData = new FormData();
	    formData.append('id', id);
	    formData.append('status', status);
		$.fn.makeMultipartRequest('POST', 'deleteProduct', false, formData, false, 'text', function(data){
			var jsonobj = $.parseJSON(data);
			var alldata = jsonobj.allOrders1;
			displayTable(alldata);
			toolTips();
			$('#inActive').prop('checked', false);
		});
	}
}

function validate(id, errorMessage)
{
	var styleBlock = '.placeholder-style.placeholder-style::-moz-placeholder {color: #cc0000;} .placeholder-style::-webkit-input-placeholder {color: #cc0000;}';
	if($('#'+id).val() ==  null || $('#'+id).val() == ""  || $('#'+id).val()=="undefined" ) {
		$('style').append(styleBlock);
		$('#'+id).css('border-color','#cc0000');
		$('#'+id).css('color','#cc0000');
		$('#'+id).attr('placeholder',errorMessage);
		$('#'+id).addClass('placeholder-style your-class');
//			$('#'+id).css('color','#cc0000');
//			$('#'+id+'Error').text(errorMessage);
	}else{
		$('#'+id).css('border-color','');
		$('#'+id).removeClass('placeholder-style your-class');
//			$('#'+id).css('color','');
//			$('#'+id+'Error').text("");
	}
	
}

function inactiveData() 
{
	var status="0";
	if($('#inActive').is(":checked") == true){
		status="0";
	}else{
		status="1";
	}
		var formData = new FormData();
		formData.append('status', status);
		
		$.fn.makeMultipartRequest('POST', 'inActiveProducts', false,
				formData, false, 'text', function(data) {
			var jsonobj = $.parseJSON(data);
			var alldata = jsonobj.allOrders1;
			displayTable(alldata);
			//console.log(jsonobj.allOrders1);
			 toolTips();
				});
		
}


function addNewTextBox()
{
	 var  dvalue =  $("#name"+k).val().trim();
	 if((dvalue == undefined) || (dvalue==''))
	 {
		 return false;
		 
	 }
	 else
		 {
			k=k+1;
	var row ="<div id="+k+"><input type='text' name='vlink' id='name"+k+"' class='form-control validate' placeholder='Videos links'/><a href='javascript:void(0);' style='color: red;' onclick='removeDependentRow("+k+");'><i class='fa fa-trash' style='color: red;text-decoration: none;cursor: pointer; float: right; margin-top:-25px; margin-right:-25px;'></i></a></div>";
	$("#dtext").append(row);
	
		 }
	
	
	}
function removeDependentRow(dependentRowCount) {
	jQuery('#' + dependentRowCount).remove();
	
	k=k-1;

	
	
}
	

document.getElementById("file1").onchange = function () {
    var reader = new FileReader();
    
    for(var i=0; i<=this.files.length; i++)
    {
     
    if(this.files[i].size>528385){
        alert("Image Size should not be greater than 528Kb");
        $("#file1").attr("src","blank");
       // $("#file1").hide();  
        $('#file1').wrap('<form>').closest('form').get(0).reset();
        $('#file1').unwrap();     
        return false; 
    } 
    if(this.files[i].type.indexOf("image")==-1){
        alert("Invalid Type");
        $("#file1").attr("src","blank");
        //$("#file1").hide();  
       $('#file1').wrap('<form>').closest('form').get(0).reset();
      //  $('#file1').unwrap();         
        return false;
    }   
    reader.onload = function (e) {
        // get loaded data and render thumbnail.
        document.getElementById("menu_image").src = e.target.result;
        $("#file1").show(); 
    };

    // read the image file as a data URL.
    reader.readAsDataURL(this.files[0]);
    

    }
}

$("#pageName").text("Product Master");
$(".product").addClass("active");
</script>