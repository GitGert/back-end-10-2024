package ee.gert.veebipood.model.payment;

import lombok.Data;

import java.util.ArrayList;
import java.util.Date;

@Data
public class EveryPayPaymentStatusBody {
    private String account_name;
    private String order_reference;
    private String email;
    private String customer_ip;
    private String customer_url;
    private Date payment_created_at;
    private double initial_amount;
    private double standing_amount;
    private String payment_reference;
    private String payment_link;
    private ArrayList<PaymentMethod> payment_methods;
    private String api_username;
    private Object warnings;
    private ProcessingError processing_error;
    private String stan;
    private int fraud_score;
    private String payment_state;
    private String payment_method;
    private String trace_id;
    private CcDetails cc_details;
    private ObDetails ob_details;
    private Date transaction_time;
    private String sca_exemption;
    private DetailedFraudCheckResults detailed_fraud_check_results;
    private String acquiring_completed_at;
}

 class CcDetails{
    private String token;
    private int bin;
    private int last_four_digits;
    private int month;
    private int year;
    private String holder_name;
    private String type;
    private String issuer_country;
    private String issuer;
    private String cobrand;
    private String funding_source;
    private String product;
    private String state_3ds;
    private int authorisation_code;
}

 class DetailedFraudCheckResults{
}

 class ObDetails{
    private String debtor_iban;
    private String creditor_iban;
    private String ob_payment_reference;
    private String ob_payment_state;
    private String token;
}

 class ProcessingError{
    private int code;
    private String message;
}

