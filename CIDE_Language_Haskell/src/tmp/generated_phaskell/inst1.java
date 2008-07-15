package tmp.generated_phaskell;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class inst1 extends inst {
  public inst1(gtycon gtycon, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<gtycon>("gtycon", gtycon)
    }, firstToken, lastToken);
  }
  public inst1(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new inst1(cloneProperties(),firstToken,lastToken);
  }
  public gtycon getGtycon() {
    return ((PropertyOne<gtycon>)getProperty("gtycon")).getValue();
  }
}
