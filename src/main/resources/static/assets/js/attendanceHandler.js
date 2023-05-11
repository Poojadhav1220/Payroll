function markAttendance(idEmp, status)
{
    var endpoint = '/admin/attendance/mark/' + idEmp + '/' + status + '/';

    axios.get(endpoint)
         .then((response) =>
      {
          var status = response.data;

          if(status == 1)
          {
              swal('Success', 'Data Saved Successfully', 'success');
          }
          else
          {
              swal('Oops!', 'Some Error occured while saving data. Please try again later.', 'error');
          }
      })
      .catch((error) =>
      {
          console.log(error);
          swal('Oops!', 'Some Error occured while saving data. Please try again later.', 'error');
      });
}