package tmp.generated_cs;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class array_creation_postfix_expressionInternal5 extends array_creation_postfix_expressionInternal {
  public array_creation_postfix_expressionInternal5(pointer_member_access pointer_member_access, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<pointer_member_access>("pointer_member_access", pointer_member_access)
    }, firstToken, lastToken);
  }
  public array_creation_postfix_expressionInternal5(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new array_creation_postfix_expressionInternal5(cloneProperties(),firstToken,lastToken);
  }
  public pointer_member_access getPointer_member_access() {
    return ((PropertyOne<pointer_member_access>)getProperty("pointer_member_access")).getValue();
  }
}
