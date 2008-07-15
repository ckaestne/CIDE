package tmp.generated_gcide;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class Mult6 extends Mult {
  public Mult6(OptionalWithDefault optionalWithDefault, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<OptionalWithDefault>("optionalWithDefault", optionalWithDefault)
    }, firstToken, lastToken);
  }
  public Mult6(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new Mult6(cloneProperties(),firstToken,lastToken);
  }
  public OptionalWithDefault getOptionalWithDefault() {
    return ((PropertyOne<OptionalWithDefault>)getProperty("optionalWithDefault")).getValue();
  }
}
