package tmp.generated_python;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class fpdef2 extends fpdef {
  public fpdef2(fplist fplist, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<fplist>("fplist", fplist)
    }, firstToken, lastToken);
  }
  public fpdef2(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new fpdef2(cloneProperties(),firstToken,lastToken);
  }
  public fplist getFplist() {
    return ((PropertyOne<fplist>)getProperty("fplist")).getValue();
  }
}
