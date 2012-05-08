#	Sample Function to add all the elements in an array and
#	return the result

@array = (1 .. 10);
my $sum = array_adder(@array);
print $sum;

sub array_adder{
	my @to_sum = @_;
	my $sum =0;
	
	foreach(@to_sum){
		$sum += $_;
	}
	return $sum;
}
