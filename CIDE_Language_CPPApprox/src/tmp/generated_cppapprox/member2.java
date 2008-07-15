package tmp.generated_cppapprox;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class member2 extends member {
  public member2(Constructor constructor, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<Constructor>("constructor", constructor)
    }, firstToken, lastToken);
  }
  public member2(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new member2(cloneProperties(),firstToken,lastToken);
  }
  public Constructor getConstructor() {
    return ((PropertyOne<Constructor>)getProperty("constructor")).getValue();
  }
}
