# Creates List of ALL possible rows

package Generator;
sub new
{
    my $class = shift;
    my $self;
    
    $length = $_[0];
        
    bless $self, $class;
    return $self;
}

sub generate{
	my $self;
	$base = 2;
	my $length = $length;
	
	@count;
	$possibles = $base ** $length;
	
	print $possibles;

	for ($index = 0; $index < $possibles; $index++){
		$temp = change_base($index, 2);
		@split = split("", $temp);
		@split = bit_flagging(@split);
		
		if( array_adder(@split) == $length ){
			push( @count, @split );
		}
	}
	return @count;
}

sub change_base{
	my $number = $_[0];
	my $new_base = $_[1];
	
	if ($number > 1){
		return change_base($number/$new_base,$new_base).$number%$new_base;
	}
	else{
		return $number%$new_base;
	}
}

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
			die "bad input!\n";
		}
	}
	return @list;
}

sub array_adder{
	my @to_sum = @_;
	my $sum =0;
	
	foreach(@to_sum){
		$sum += $_;
	}
	return $sum;
}
1;
