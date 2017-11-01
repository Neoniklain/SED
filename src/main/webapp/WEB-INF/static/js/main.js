$(function() {

    $('#login-form-link').click(function(e) {
		$("#login-form").delay(100).fadeIn(100);
 		$("#register-form").fadeOut(100);
		$('#register-form-link').removeClass('active');
		$(this).addClass('active');
		e.preventDefault();
	});
	$('#register-form-link').click(function(e) {
		$("#register-form").delay(100).fadeIn(100);
 		$("#login-form").fadeOut(100);
		$('#login-form-link').removeClass('active');
		$(this).addClass('active');
		e.preventDefault();
	});

});

$(function() {
    $('#default').puigrowl();
    $('#btn-info').puibutton().click(function() {
        addMessage([{severity: 'info', summary: 'Message Title', detail: 'Message Detail here.'}]);
    });
    $('#btn-warn').puibutton().click(function() {
        addMessage([{severity: 'warn', summary: 'Message Title', detail: 'Message Detail here.'}]);
    });
    $('#btn-error').puibutton().click(function() {
        addMessage([{severity: 'error', summary: 'Message Title', detail: 'Message Detail here.'}]);
    });
    $('#btn-multiple').puibutton().click(function() {
        addMessage([{severity: 'info', summary: 'Message Title', detail: 'Message Detail here.'}
            , {severity: 'warn', summary: 'Message Title', detail: 'Message Detail here.'}
            , {severity: 'error', summary: 'Message Title', detail: 'Message Detail here.'}]);
    });
    addMessage = function(msg) {
        $('#default').puigrowl('show', msg);
    };
});