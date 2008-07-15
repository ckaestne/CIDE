package tmp.generated_cs;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class primary_expression_postfixInternal1 extends primary_expression_postfixInternal {
  public primary_expression_postfixInternal1(member_access member_access, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<member_access>("member_access", member_access)
    }, firstToken, lastToken);
  }
  public primary_expression_postfixInternal1(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new primary_expression_postfixInternal1(cloneProperties(),firstToken,lastToken);
  }
  public member_access getMember_access() {
    return ((PropertyOne<member_access>)getProperty("member_access")).getValue();
  }
}
