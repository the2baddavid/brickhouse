#	Sample of method that changes base from one specified to another
#	As well as a driver for the function

$answer = change_base(20,2);
print $answer;

#	Converts number from base-10 to base-2
#	Uses Recursion && modulo function
sub change_base
{
	my $number = $_[0];
	my $new_base = $_[1];
	
	if ($number > 1){
		return change_base($number/$new_base,$new_base).$number%$new_base;
	}
	else{
		return $a%$new_base;
	}
}
	
