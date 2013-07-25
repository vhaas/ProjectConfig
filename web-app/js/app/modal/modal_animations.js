App.animateModalClose = function() {

	var closeModalPromise = new Ember.RSVP.Promise(function(resolve, reject) {

		$('.modal.in').removeClass('in');
		$('.modal-backdrop.in').removeClass('in');

		setTimeout(function() {
			resolve(true);
		}, App.DEFAULT_CSS_TRANSITION_DURATION_MS);

	});

	return closeModalPromise;
};

App.animateModalOpen = function() {

	var openModalPromise = new Ember.RSVP.Promise(function(resolve, reject) {
		$('.modal').addClass('in');
		$('.modal-backdrop').addClass('in');

		setTimeout(function() {
			resolve(true);
		}, App.DEFAULT_CSS_TRANSITION_DURATION_MS);
	});

	return openModalPromise;
};