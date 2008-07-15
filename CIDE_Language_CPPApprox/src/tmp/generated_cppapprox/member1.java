package tmp.generated_cppapprox;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class member1 extends member {
  public member1(access_specifier access_specifier, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<access_specifier>("access_specifier", access_specifier)
    }, firstToken, lastToken);
  }
  public member1(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new member1(cloneProperties(),firstToken,lastToken);
  }
  public access_specifier getAccess_specifier() {
    return ((PropertyOne<access_specifier>)getProperty("access_specifier")).getValue();
  }
}
