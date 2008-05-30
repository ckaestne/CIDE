package tmp.generated_dtd;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class SeqOrChoice1 extends SeqOrChoice {
  public SeqOrChoice1(Choice choice, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<Choice>("choice", choice)
    }, firstToken, lastToken);
  }
  public SeqOrChoice1(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public ASTNode deepCopy() {
    return new SeqOrChoice1(cloneProperties(),firstToken,lastToken);
  }
  public Choice getChoice() {
    return ((PropertyOne<Choice>)getProperty("choice")).getValue();
  }
}
