package tmp.generated_haskell;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class varMain2 extends varMain {
  public varMain2(varsym varsym, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<varsym>("varsym", varsym)
    }, firstToken, lastToken);
  }
  public varMain2(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new varMain2(cloneProperties(),firstToken,lastToken);
  }
  public varsym getVarsym() {
    return ((PropertyOne<varsym>)getProperty("varsym")).getValue();
  }
}
