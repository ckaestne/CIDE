package tmp.generated_cs;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class base_access1 extends base_access {
  public base_access1(member_access member_access, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<member_access>("member_access", member_access)
    }, firstToken, lastToken);
  }
  public base_access1(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new base_access1(cloneProperties(),firstToken,lastToken);
  }
  public member_access getMember_access() {
    return ((PropertyOne<member_access>)getProperty("member_access")).getValue();
  }
}
