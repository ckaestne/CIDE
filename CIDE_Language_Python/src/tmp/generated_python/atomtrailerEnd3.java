package tmp.generated_python;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class atomtrailerEnd3 extends atomtrailerEnd {
  public atomtrailerEnd3(subscriptlist subscriptlist, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<subscriptlist>("subscriptlist", subscriptlist)
    }, firstToken, lastToken);
  }
  public atomtrailerEnd3(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new atomtrailerEnd3(cloneProperties(),firstToken,lastToken);
  }
  public subscriptlist getSubscriptlist() {
    return ((PropertyOne<subscriptlist>)getProperty("subscriptlist")).getValue();
  }
}
