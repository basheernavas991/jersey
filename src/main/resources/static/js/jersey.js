//Stallion Custom Js functionalities.
$( document ).ready(function() {
	
	/**
	 * Sending csrf token along with every Ajax response
	 */
	var token = $("input[name='_csrf']").val();
	var header = "X-CSRF-TOKEN";
	$(document).ajaxSend(function(e, xhr, options) {
		xhr.setRequestHeader(header, token);
	});

	$(function() {
		
	    $('.confirm').click(function(e) {
	    	e.preventDefault();
	        return window.confirm("Are you sure?");
	    });
	});

});

/**
 * Blocking screen during AJAX
 * Block UI will be triggered on every Ajax Start and unblockUI when ajax stops
 * For Exceptions, set the block UI to false in individual AJAX calls and set the flag back to true on success
 * Eg: Autocomplete (select2) Ajax calls
 */
var blockUI = true;

$(document).ajaxStart(function(){
    if(blockUI){
    	$.blockUI({
    		message: null,
    		overlayCSS:  { 
    	        opacity: 0.2, 
    	    },
    		baseZ: 20000,
    	});
    }
}).ajaxStop($.unblockUI);

/**
 * Format Date to dd/mm/yyyy HH:mm:ss format
 * @param data
 * @returns Formatted Date Time
 * @author navas
 */
function stFormatDateTime(data){
	 if(data == null || data == ""){
		 return "";
	 }
	 var date = new Date(data);
     var month = date.getMonth() + 1;
     return date.getDate() + "/" + (month.length > 1 ? month : "0" + month) + "/" + date.getFullYear() + " "+date.getHours()+":"+date.getMinutes()+":"+date.getSeconds();
}


/**
 * Format Date to dd/mm/yyyy format
 * @param data
 * @returns Formatted Date
 * @author navas
 */
function stFormatDate(data){
	 if(data == null || data == ""){
		 return "";
	 }
	 var date = new Date(data);
     var month = date.getMonth() + 1;
     return date.getDate() + "/" + (String(month).length > 1 ? month : "0" + month) + "/" + date.getFullYear();
}


/**
 * Initialize the Date Picker
 * format: 'DD/MM/YYYY',
 * @param id
 * @returns
 */
function stInitDatePicker(id){
	$(id).daterangepicker({
		locale: {
		      format: 'DD/MM/YYYY',
		},
	    autoUpdateInput: false,
		singleDatePicker: true,
		showDropdowns: true
	}); 
	
	$(id).on('apply.daterangepicker', function(ev, picker) {
	      $(this).val(picker.startDate.format('DD/MM/YYYY'));
	});
}


/**
 * Compute Age
 * @param date
 * @returns computed Age
 */
function stComputeAge(date){
	
	var split = date.split('/');
	var formattedDate = new Date([split[1], split[0], split[2]].join('/'));
	var year  = formattedDate.getFullYear();  
    var curYear = new Date().getFullYear();
    return curYear - year;
}

/**
 * Compute date After some days of a given date
 * @param date
 * @param days
 * @returns computed date
 */
function stComputeDate(days , date){
	var split = date.split('/');
	var formattedDate = new Date([split[1], split[0], split[2]].join('/'));
	
    var newdate = new Date(formattedDate);

    newdate.setDate(newdate.getDate() + parseInt(days));
    var dd = newdate.getDate();
    var mm = newdate.getMonth() + 1;
    var y = newdate.getFullYear();

    var someFormattedDate = dd + '/' + mm + '/' + y;
	
    return someFormattedDate;
}

/**
 * Stallion JGrowl
 */
window.showGrowlSuccess = function(message){
	createGrowl(message, 'qtip-green');
}
window.showGrowlError = function(message){
	createGrowl(message, 'qtip-red');
}

window.createGrowl = function(message, theme) {
    var target = $('.qtip.jgrowl:visible:last');

    $('<div/>').qtip({
        content: {
            text: message,
            title: {
                text: 'Attention!',
                button: true
            }
        },
        position: {
            target: [0,0],
            container: $('#qtip-growl-container')
        },
        show: {
            event: false,
            ready: true,
            effect: function() {
                $(this).stop(0, 1).animate({ height: 'toggle' }, 400, 'swing');
            },
            delay: 0
        },
        hide: {
            event: false,
            inactive: 5000,
            effect: function(api) {
                $(this).stop(0, 1).animate({ height: 'toggle' }, 400, 'swing');
            }
        },
        style: {
            width: 250,
            classes: theme,
            tip: false
        }
    });
}

/**
 * Serialize Object 
 * Converts object with "name" and "value" keys
 * into object with "name" key having "value" as value
 * 
 */
$.fn.serializeObject = function(){
   var obj = {};
    
   $.each( this.serializeArray(), function(i,o){
      var n = o.name, v = o.value;
        
      obj[n] = obj[n] === undefined ? v
         : $.isArray( obj[n] ) ? obj[n].concat( v )
         : [ obj[n], v ];
   });
    
   return obj;
}; 

/**
 * SerializeForm and Append on data table data
 * @param dataTableData
 * @param form
 * @author navas
 */
function stSerializeFormForDataTable(dataTableData, form){
	
	var formData = form.serializeArray();
    var finalFormData = {}; 

    // Generating multimap to serialize in traditional way
    for (var i in formData) {
      var field = formData[i];
      var existing = finalFormData[field["name"]];
      if (existing) {
        existing.push(field["value"]);
        finalFormData[field["name"]] = existing;
      } else {
        finalFormData[field["name"]] = [field["value"]];
      }
    }
    // Adding final data, serialized in traditional way, to Datatables data, serialized in non-traditional way
    return $.param(dataTableData) + "&" + $.param(finalFormData, true);
}

/**
 * SerializeForm 
 * @param dataTableData
 * @param form
 * @author navas
 */
function stSerializeForm(form){
	
	  var o = {};
      var a = form.serializeArray();
      $.each(a, function () {
          if (o[this.name]) {
              if (!o[this.name].push) {
                  o[this.name] = o[this.name]+','+this.value;
              }
          } else {
              o[this.name] = this.value || '';
          }
      });
      return o;
}





