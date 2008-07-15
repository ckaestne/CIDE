package tmp.generated_ecmascript;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class FunctionBody extends GenASTNode {
  public FunctionBody(SourceElements sourceElements, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyZeroOrOne<SourceElements>("sourceElements", sourceElements)
    }, firstToken, lastToken);
  }
  public FunctionBody(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new FunctionBody(cloneProperties(),firstToken,lastToken);
  }
  public SourceElements getSourceElements() {
    return ((PropertyZeroOrOne<SourceElements>)getProperty("sourceElements")).getValue();
  }
}
