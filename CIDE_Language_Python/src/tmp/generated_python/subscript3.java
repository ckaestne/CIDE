package tmp.generated_python;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class subscript3 extends subscript {
  public subscript3(slice slice1, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<slice>("slice1", slice1)
    }, firstToken, lastToken);
  }
  public subscript3(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new subscript3(cloneProperties(),firstToken,lastToken);
  }
  public slice getSlice1() {
    return ((PropertyOne<slice>)getProperty("slice1")).getValue();
  }
}
