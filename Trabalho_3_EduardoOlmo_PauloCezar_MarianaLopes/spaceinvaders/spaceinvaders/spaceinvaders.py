#GRUPO: Eduardo Olmo Santana, Paulo Cezar Rocha Furtado, Mariana Lopes de Gouvea

import os
import sys
import pygame
from random import randint

pygame.init()

os.environ['SDL_VIDEO_CENTERED'] = '1'					# Imagem centralizada na tela

size = width, height = (1024,768) 						# Tamanho da janela
screen = pygame.display.set_mode(size)	# Cria a Janela
pygame.mouse.set_visible(False)							# Esconde o cursor do mouse durante o jogo
black = 0, 0, 0											# Cor preta no sistema RGB (Red/Green/Blue)

'''
Classe "Nave"

A primeira classe eh muito simples, herdada da classe Sprite (nativa do PyGame). Um sprite eh um elemento grafico do jogo, e possui 2 propriedades importantes: image e rect. Image e' a imagem em si, carregada do disco (os principais formatos sao suportados, tais como JPG, GIF, PNG e BMP) e rect representa o retangulo virtual que contem a imagem (imagine um retangulo circunscrito a imagem). Alterando as propriedades centerx, centery, top, bottom, left e right de rect podemos posicionar o sprite onde quisermos na tela.
'''

class Nave(pygame.sprite.Sprite):
	def __init__(self,imagem): 								# __init__ eh o construtor em Python
		pygame.sprite.Sprite.__init__(self)				# chamando o construtor da superclasse
		self.image = pygame.image.load(imagem) # Define qual imagem serah desenhada na tela
		self.rect = self.image.get_rect()				# Cria um retangulo (invisivel) em torno da imagem
		self.rect.centerx = width/2						# Coord x inicial da imagem
		self.rect.centery = height-35					# Coord y inicial da imagem

'''
Classe "Invasor"

A segunda classe eh bem similar. A unica diferenca eh no metodo update, que faz com que o invasor "passeie" pela tela. Alem disso, caso o invasor atinja um canto da tela, ele muda de direcao.

O metodo "update" eh executado a cada loop do jogo para alterar a posicao na qual o objeto deve ser desenhado. Vale lembrar que no nosso caso a origem do eixo esta no canto superior esquerdo da tela.
'''

class Invasor(pygame.sprite.Sprite):
	speed = (0,0)
	def __init__(self, images):
		pygame.sprite.Sprite.__init__(self)
		self.image = images[0] 							# lista com varias imagens (para que o objeto se "mexa")
		self.rect = self.image.get_rect()
		self.rect.centerx = randint(50,width-50)
		self.rect.centery = randint(35,height/2-5)
		self.speed = [randint(-4,4),randint(-2,2)]		# a velocidade de movimento do invasor eh aleatoria
		# se speed=(4, -2), por exemplo, entao o objeto eh movido 4 unidades na direcao x e -2 unidades na direcao y.

	def update(self, tempo, images):
		self.rect.move_ip(self.speed)					# muda a posicao do objeto de acordo com o atributo. 

		if tempo==0: # de tempos em tempos (a cada 40 ciclos), a velocidade do objeto vai mudar
			self.speed[0] = randint(-4,4)
			self.speed[1] = randint(-2,2)

		if self.rect.centerx > width or self.rect.centerx < 0: 
			# se o objeto chegar no canto esquerdo ou direito, sua velocidade no eixo x eh invertida.
			self.speed[0] = -self.speed[0] 
		if self.rect.top < 0 or self.rect.bottom > 2*(height/3):
			# caso o objeto atinja o topo ou o 2/3 do fundo da tela, sua velocidade no eixo y eh invertida.
			self.speed[1] = -self.speed[1]

		if tempo < 20: 
			# a cada 20 ciclos, a imagem do objeto muda, para dar a sensacao de que ele se mexe
			self.image = images[0] 
		else: 
			self.image = images[1]
		
		
		
		
'''
Classe "Tiro"

Como na classe Invasor, ha o construtor e o metodo update.
'''

class Tiro(pygame.sprite.Sprite):
	def __init__(self,imagem):
		pygame.sprite.Sprite.__init__(self)
		self.image = pygame.image.load(imagem)
		self.rect = self.image.get_rect()
		self.rect.centery = height-70
		self.speed = [0,0]

	def update(self,objetos,tiros):
		self.rect.move_ip(self.speed)

		if self.rect.top < 0 and self in objetos: 
			# se o tiro atingir o topo da tela, ele desaparece e volta a ficar parado em sua posicao inicial
			self.rect.centery = height-70				# posicao inicial
			self.speed = [0,0]							# velocidade parada
			objetos.remove(self) 						# removido da lista de objetos que serao exibidos (para sumir da tela)
			return tiros+1 								# se o usuario errou um tiro, ele tem uma chance a menos

		return tiros # se o tiro ainda nao saiu da tela, o usuario ainda tem chance de acerta-lo.
		
'''
Classe "Fundo"

Apenas desenha uma imagem no centro da tela.
'''

class Fundo(pygame.sprite.Sprite):
	def __init__(self,img):
		pygame.sprite.Sprite.__init__(self)
		self.image = pygame.image.load(img)
		self.rect = self.image.get_rect()
		self.rect.centerx = width/2
		self.rect.centery = height/2


def main(args):
	
	pygame.key.set_repeat(1,1)

	# Criando a lista com as duas imagens do invasor que serao usadas para desenha-lo
	imagens_invasor1 = [pygame.image.load('ff2.png'), pygame.image.load('ff2.png')] 

	# Criando um objeto da classe Invasor
	invasor1 = Invasor(imagens_invasor1) 
	invasor2 = Invasor(imagens_invasor1)
	invasor3 = Invasor(imagens_invasor1)
	invasor4 = Invasor(imagens_invasor1)
	invasor5 = Invasor(imagens_invasor1)
	invasor6 = Invasor(imagens_invasor1)
	
	# Criando um objeto da classe Nave
	n = Nave('a.png')
	n2 = Nave('v.png')
	
	# Criando um objeto da classe Tiro, inicialmente parado
	t = Tiro('taa2.png')
	t2 = Tiro('tvv3.png')
	
	space = pygame.image.load('f2.png')
	
	acertos = 0

	# Lista com os objetos a serem desenhados. A principio, o tiro nao eh desenhado.
	objetos = [n, n2] 
	invasores = [invasor1,invasor2,invasor3,invasor4,invasor5,invasor6]
	
	# Define o titulo da janela
	pygame.display.set_caption('Space Invaders!') 
	clock = pygame.time.Clock()
	# pygame.key.set_repeat(1,1)

	acertou = False 			# indica se o usuario ja acertou um tiro no invasor
	tiros = 0					# quantidade de tiros gastos pelo usuario
	tempo = 0					# contador para controlar o tempo de mudar a imagem do invasor
	
	while tiros < 30:
		clock.tick(120)			# garante que o programa nao vai rodar a mais que 120fps
		tempo = (tempo+1)%40	# o tempo varia sempre entre 0 e 39 (portanto, sao 40 ciclos)
		
		''' O jogo tambem precisa detectar quando o jogador apertar as setas Q ou ESC (para terminar o jogo),
		quando o jogador movimentar o mouse e quando o jogador apertar algum botao do mouse. 
		Isso eh feito atraves do seguinte trecho de codigo: '''
	
		# O centro da nave no eixo x eh sempre a posicao x de onde estiver o mouse
		#n.rect.centerx = pygame.mouse.get_pos()[0] 
		
		for event in pygame.event.get():

			if event.type == pygame.KEYDOWN:
				# Se alguma tecla for apertada no teclado
				
				if t not in objetos:
					if event.key == pygame.K_p:
						#n.image = pygame.image.load('tiroazul.png')
						t.speed = [0,-8]							# define a velocidade y do tiro
						t.rect.centerx = n.rect.centerx				# posiciona o tiro no eixo x onde ele foi disparado
						objetos.append(t) 							# inclui o tiro na lista de objetos a serem desenhados
				
				
				if t2 not in objetos:	
					if event.key == pygame.K_SPACE:
						#n2.image = pygame.image.load('tirovermelho.png')
						t2.speed = [0,-8]							# define a velocidade y do tiro
						t2.rect.centerx = n2.rect.centerx			# posiciona o tiro no eixo x onde ele foi disparado
						objetos.append(t2) 							# inclui o tiro na lista de objetos a serem desenhados
						
						
				if event.key == pygame.K_ESCAPE or event.key == pygame.K_q:
					#Veja todas as teclas em http://www.pygame.org/docs/ref/key.html
					print('Bye')
					sys.exit()
				if event.key == pygame.K_LEFT:
					if n.rect.left >= 0: 
						n.rect.move_ip([-1,0])
				if event.key == pygame.K_RIGHT:
					if n.rect.right <= width:
						n.rect.move_ip([1,0])
				if event.key == pygame.K_a:
					if n2.rect.left >= 0: 
						n2.rect.move_ip([-1,0])
				if event.key == pygame.K_d:
					if n2.rect.right <= width:
						n2.rect.move_ip([1,0])

			elif event.type == pygame.QUIT:
				print('Bye')
				sys.exit()
			
		''' Agora vamos atualizar as posicoes do tiro e do invasor a cada loop do jogo,
		utilizando o metodo update criado em suas classes.'''
		
		for i in invasores:
			i.update(tempo,imagens_invasor1)				#atualiza a posicao dos invasores
		
		tiros = t.update(objetos, tiros) 					# atualiza a posicao do tiro
		tiros = t2.update(objetos, tiros)

		''' Agora vamos detectar se o tiro colidiu com a nave. Nesse caso, o jogo acaba e o usuario venceu. 
		Existem varias formas de detectar colisoes de sprites. A mais simples eh: 
		sprite.rect.colliderect(sprite.rect)
		Este metodo retorna "True" caso os retangulos dos 2 sprites estejam se sobrepondo (ou seja, colidindo).'''
		
		for atingido in invasores:
			
			if t.rect.colliderect(atingido.rect):
				invasores.remove(atingido)
				objetos.remove(t)
				t.rect.centery = height-70		
			if t2.rect.colliderect(atingido.rect):
				invasores.remove(atingido)
				objetos.remove(t2)
				t2.rect.centery = height-70		
			
		if len(invasores) == 0: 
			f = Fundo('images/youwin.jpg')
			break
		
		''' Por fim, o programa desenha todos os objetos na tela a cada loop do jogo. 
		Apos calculadas as novas posicoes de cada um, a forma mais eficiente de atualizarmos a tela eh preenche-la 
		com o fundo preto, para que os objetos antigos (da iteracao anterior) sejam apagados: isso eh muito mais 
		simples do que ter que apaga-los um a um. O metodo blit "renderiza" cada objeto na tela, e quando todos 
		estiverem prontos o metodo flip efetivamente atualiza a tela para o jogador.'''

		screen.blit(space,(0,0))
		for objeto in objetos: 
			screen.blit(objeto.image, objeto.rect)			# desenha cada objeto da lista de objetos a serem desenhados
		
		for inv in invasores:
			screen.blit(inv.image,inv.rect)
		
		pygame.display.flip()


	'''
	O CODIGO A PARTIR DAQUI SO SERA EXECUTADO QUANDO UM TIRO ACERTAR O INVASOR, OU QUANDO ACABAREM OS TIROS
	'''

	
	if len(invasores) != 0: f = Fundo('images/gameover.jpg')

	while True:
		for event in pygame.event.get():
			if event.type == pygame.QUIT:
				print('Bye')
				sys.exit()
			elif event.type == pygame.MOUSEBUTTONDOWN:
				print('Bye')
				sys.exit()
			elif event.type == pygame.KEYDOWN:
				if event.key == pygame.K_ESCAPE or event.key == pygame.K_q or event.key == pygame.K_RETURN:
					print('Bye')
					sys.exit()
	
		screen.blit(f.image, f.rect)
		pygame.display.flip()
	


if __name__ == '__main__':
	import sys
	sys.exit(main(sys.argv))


