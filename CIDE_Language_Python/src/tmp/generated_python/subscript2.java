package tmp.generated_python;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class subscript2 extends subscript {
  public subscript2(test test, slice slice, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<test>("test", test),
      new PropertyZeroOrOne<slice>("slice", slice)
    }, firstToken, lastToken);
  }
  public subscript2(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new subscript2(cloneProperties(),firstToken,lastToken);
  }
  public test getTest() {
    return ((PropertyOne<test>)getProperty("test")).getValue();
  }
  public slice getSlice() {
    return ((PropertyZeroOrOne<slice>)getProperty("slice")).getValue();
  }
}
