$(document).ready( function () {
	 var table = $('#employeesTable').DataTable({
			"sAjaxSource": "/emps",
			"sAjaxDataProp": "",
			"order": [[ 0, "asc" ]],
			"aoColumns": [
			      { "mData": "eid"},
		          { "mData": "name" },
				  { "mData": "did" },
				  { "mData": "job" },
				  { "mData": "sal" },
				  { "mData": "comm" }
			]
	 })
});