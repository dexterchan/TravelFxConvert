/**
 * 
 */

var fxQuote = new Vue({
	el : '#fxQuote',
	data : {
		message : '',
		quote : {
			ccy1 : '',
			ccy2 : ''
		},
		ccyset:[],
		errorMessage:''
	},
	created : function() {
		
		this.clearMessage();
		this.fetchEvents();
	},
	methods : {
		quoteFX : function() {
			this.clearMessage();
			this.message = "Please Wait...";
			this.quote.ccy1 = this.quote.ccy1.toUpperCase().trim();
			this.quote.ccy2 = this.quote.ccy2.toUpperCase().trim();
			if (this.quote.ccy1 && this.quote.ccy2) {
				/*
				this.$http.post('/quotefxpair', this.quote).success(
						function(res) {
							this.message = res;
						}).error(function(err) {
					console.log(err);
				});
				*/
				this.$http.post('/quotefxpair', this.quote).then(
						(response) => {
							// get status
						    response.status;
						    // get status text
						    response.statusText;
							//this.$set('ccyset', response.body);
						    this.message = response.json();
							console.log(response);
						},
						(response) => {
							//$("#errorMsg").show();
							
							if(response.status==500){
								this.message="Service error";
								this.errorMessage=response.json().message;
							}else{
								this.message=" cannot be retrieved";
								this.errorMessage=response.json().message;
							}
							
							console.log(response);
						}
				);
			}

		},
		fetchEvents : function() {
			this.clearMessage();
			/*
			this.$http.get('/allccyset').success(function(ccyset) {
				this.$set('ccyset', ccyset);
				console.log(ccyset);
			}).error(function(err) {
				console.log(err);
			});
			*/
			this.$http.get('/allccyset').then(
					(response) => {
						// get status
					    response.status;
					    // get status text
					    response.statusText;
						//this.$set('ccyset', response.body);
					    this.ccyset = response.json();
						console.log(response);
					},(response) => {
					
						console.log(response);
					}
			);
		},
		clearMessage:function(){
			this.errorMessage="";
			this.message="";
			//$("#errorMsg").hide();
			
		}
	}
})