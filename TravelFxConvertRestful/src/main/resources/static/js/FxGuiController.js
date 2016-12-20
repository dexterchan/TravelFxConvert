/**
 * 
 */


var fxQuote = new Vue({
	  el: '#fxQuote',
	  data: {
	    message: '',
	    quote: { ccy1: '', ccy2: '' }
	  },
	  methods: {
		  quoteFX: function () {
		      this.message = 1.00;
		      
		      if(this.quote.ccy1 && this.quote.ccy2){
		    	  this.$http.post('/quotefxpair', this.quote)
		          .success(function (res) {
		            this.message=res;
		          })
		          .error(function (err) {
		            console.log(err);
		          });
		      }
		      
		    }
		  }
	})