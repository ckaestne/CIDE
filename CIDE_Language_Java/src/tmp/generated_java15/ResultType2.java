package tmp.generated_java15;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class ResultType2 extends ResultType {
  public ResultType2(Type type, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<Type>("type", type)
    }, firstToken, lastToken);
  }
  public ResultType2(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new ResultType2(cloneProperties(),firstToken,lastToken);
  }
  public Type getType() {
    return ((PropertyOne<Type>)getProperty("type")).getValue();
  }
}
