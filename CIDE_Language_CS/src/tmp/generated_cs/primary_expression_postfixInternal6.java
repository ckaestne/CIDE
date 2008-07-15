package tmp.generated_cs;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class primary_expression_postfixInternal6 extends primary_expression_postfixInternal {
  public primary_expression_postfixInternal6(pointer_member_access pointer_member_access, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<pointer_member_access>("pointer_member_access", pointer_member_access)
    }, firstToken, lastToken);
  }
  public primary_expression_postfixInternal6(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new primary_expression_postfixInternal6(cloneProperties(),firstToken,lastToken);
  }
  public pointer_member_access getPointer_member_access() {
    return ((PropertyOne<pointer_member_access>)getProperty("pointer_member_access")).getValue();
  }
}
