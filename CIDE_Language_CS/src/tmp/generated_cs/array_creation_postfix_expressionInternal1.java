package tmp.generated_cs;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class array_creation_postfix_expressionInternal1 extends array_creation_postfix_expressionInternal {
  public array_creation_postfix_expressionInternal1(member_access member_access, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<member_access>("member_access", member_access)
    }, firstToken, lastToken);
  }
  public array_creation_postfix_expressionInternal1(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new array_creation_postfix_expressionInternal1(cloneProperties(),firstToken,lastToken);
  }
  public member_access getMember_access() {
    return ((PropertyOne<member_access>)getProperty("member_access")).getValue();
  }
}
