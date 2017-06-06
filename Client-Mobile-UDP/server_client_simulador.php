<?php


//AQUI O CODIGO EM PHP DEVE RECEBER AS VARIAVEIS LAT E LNG EM VARIAVEIS PARA MANIPULAR EM PHP E EXECUTAR O RESTANTE DO CODIGO 
	
	$lat = $_POST["lat"];
	$lng = $_POST["lng"];


/*
 * Simple php udp socket client
 */
function getmicrotime()
{
	list($usec, $sec) = explode(" ",microtime());
	return ((float)$usec + (float)$sec);
}

// Reduce errors
error_reporting( ~E_WARNING );

$server = 'localhost';
$port = 2006;

$sock = socket_create(AF_INET, SOCK_DGRAM, 0);

	echo 'Data : 1#2017#05#20#01#20#50#-1.1#-1.1';
	echo "\n";
	$data = '1#2017#05#20#01#20#50#'.$lat.'#'.$lng;//fgets(STDIN);

	echo "\n";
	$count = 2;//fgets(STDIN);

	$data = rtrim($data, "\r\n");
	$count = rtrim($count, "\r\n");

	$total_time = 0;

	for($i = 1; $i <= $count ; $i++)
	{
		// Send the message to the server
		$time_start = getmicrotime();
		socket_sendto( $sock, $data , strlen($data) , 0 , $server , $port );

		// Now receive reply from server and print it
		socket_recv ( $sock , $reply , 2045 , MSG_WAITALL );

		$time_taken = round(getmicrotime() - $time_start, 7);
		$time_taken *= 1000;

		// print reply from server
		echo "$reply from $server: icmp_req=$i time=$time_taken ms\n";

		$total_time += $time_taken;

		array_push($packet_time, $time_taken);

		sleep(1); // delay 1 seconds
	}

?>
