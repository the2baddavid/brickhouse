#	Sample function that uses bit flags to change binary to another
#	set of numbers, and a sample driver for function
@list = split("", "0100110");

bit_flagging(@list);

sub bit_flagging{
	my @list = @_;

	foreach(@list){
		if($_ == 0){
			$_ = 3;
		}
		elsif($_ == 1){
			$_ = 4.5;
		}
		else{
			print "bad input!\n";
		}
	}
	return @list;
}
