package tmp.generated_cs;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class primary_expression_start4 extends primary_expression_start {
  public primary_expression_start4(predefined_type predefined_type, member_access member_access, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<predefined_type>("predefined_type", predefined_type),
      new PropertyOne<member_access>("member_access", member_access)
    }, firstToken, lastToken);
  }
  public primary_expression_start4(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new primary_expression_start4(cloneProperties(),firstToken,lastToken);
  }
  public predefined_type getPredefined_type() {
    return ((PropertyOne<predefined_type>)getProperty("predefined_type")).getValue();
  }
  public member_access getMember_access() {
    return ((PropertyOne<member_access>)getProperty("member_access")).getValue();
  }
}
